<?php namespace App\Http\Controllers;

use App\Article;
use App\ContentArea;
use App\CSSTemplate;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Http\Requests\WebPageRequest;
use App\WebPage;
use Illuminate\Http\Request;
use Auth;

class WebPagesController extends Controller {

    public function index()
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $webPages = WebPage::all();
            return view('webpages.index', compact('webPages'));
        }
        else
            return view('auth.login');
    }
    public function show($id)
    {
        $contentAreas = ContentArea::oldest('display_order')->get();
        $size = $contentAreas[0]->id - sizeof($contentAreas);
        //should've named this oldest. oops.
        $latest = ContentArea::oldest()->first();
        $articles = Article::latest('created_at')->get();
        $cssTemplate = CSSTemplate::where('active_state', '=', 1)->first();
        if(is_numeric($id))
            $webPage = WebPage::find($id);
        else
        {
            $col = 'alias';
            $webPage = WebPage::where($col, '=', $id)->first();
        }
        return view('webpages.show', compact('webPage', 'contentAreas', 'articles', 'cssTemplate', 'latest', 'size'));
    }
    public function store(WebPageRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $this->createWebPage($request);
            return redirect('webpages');
        }
        else
            return view('auth.login');

    }
    public function create()
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            return view('webpages.create');
        }
        else
            return view('auth.login');
    }
    public function edit($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            if(is_numeric($id))
                $webPage = WebPage::find($id);
            else
            {
                $col = 'alias';
                $webPage = WebPage::where($col, '=', $id)->first();
            }
            $webPage->modified_by = Auth::user()->first_name . " " . Auth::user()->last_name;
            $webPage->save();
            return view('webpages.edit', compact('webPage'));
        }
        else
            return view('auth.login');

    }
    public function update($id, WebPageRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            if(is_numeric($id))
                $webPage = WebPage::find($id);
            else
            {
                $col = 'alias';
                $webPage = WebPage::where($col, '=', $id)->first();
            }
            //not sure why this gives an error. works as it should.
            $webPage->update($request->all());
            \Session::flash('flash_message', 'Your page has been updated!');
            return redirect('webpages');
        }
        else
            return view('auth.login');

    }
    public function createWebPage(WebPageRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $webPage = WebPage::create($request->all());
            $webPage->user()->associate(Auth::user());
            $webPage->save();
            \Session::flash('flash_message', 'Your page has been created!');
            return redirect('webpages');
        }
        else
            return view('auth.login');
    }
    public function destroy($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $affectedRows = WebPage::where('id', '=', $id)->delete();
            $articles = Article::where('webpage', '=', $id)->get();
            foreach($articles as $article)
            {
                $article->webpage = 0;
                $article->save();
            }
            return $affectedRows;
        }
        else
            return view('auth.login');

    }
}
