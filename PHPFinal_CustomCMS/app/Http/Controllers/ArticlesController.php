<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Article;
use App\Http\Requests\ArticleRequest;
use Auth;
use Illuminate\Http\Request;
use App\ContentArea;
use App\WebPage;

class ArticlesController extends Controller {

	public function index()
    {
        //only editors can view articles in backend
        if(Auth::check() && (Auth::user()->isEditor()))
        {
            $articles = Article::latest('created_at')->get();
            $latest = Article::latest()->first();
            return view('articles/index', compact('articles', 'latest'));
        }
        else
            return view('auth/login');

    }
    public function show($id)
    {
        //option to view in frontend for authors, backend for editors
        if(Auth::user()->isAuthor() || Auth::user()->isEditor())
        {
            $article = Article::find($id);
            return view('articles/show', compact('article'));
        }
        else
            return view('auth/login');

    }
    public function create()
    {
        if(Auth::check() && (Auth::user()->isAuthor() || Auth::user()->isEditor()))
        {
            $contentAreas = ContentArea::lists('alias');
            array_push($contentAreas, "none");
            $options = array();
            array_push($options, "all");
            array_push($options, "none");
            $webPages = WebPage::lists('alias');
            $options = array_merge($options, $webPages);
            return view('articles.create', compact('contentAreas', 'options'));
        }
        else
            return view('auth.login');
    }
    public function store(ArticleRequest $request)
    {
        if(Auth::user()->isAuthor() || Auth::user()->isEditor())
        {
            $this->createArticle($request);
            return redirect('articles');
        }
        else
            return view('auth.login');
    }
    public function edit($id)
    {
            //Author edit option appears in frontend, Editors must be in backend
            if(Auth::check() && (Auth::user()->isAuthor() || Auth::user()->isEditor()))
            {
                $article = Article::find($id);
                $contentAreas = ContentArea::lists('alias');
                array_push($contentAreas, "none");
                $pageOptions = array();
                array_push($pageOptions, "all", "none");
                $webPages = WebPage::lists('id');
                $pageOptions = array_merge($pageOptions, $webPages);
                $article->modified_by = Auth::user()->first_name . " " . Auth::user()->last_name;
                $article->save();
                return view('articles.edit', compact('article', 'contentAreas', 'pageOptions'));
            }
            else
                return view('auth/login');
    }
    public function update($id, ArticleRequest $request)
    {
        if(Auth::user()->isAuthor() || Auth::user()->isEditor())
        {
            $article = Article::find($id);
            //works fine.
            $article->update($request->all());
            \Session::flash('flash_message', 'Article has been updated successfully!');
            return redirect('articles');
        }
        else
            return view('auth.login');
    }
    public function createArticle(ArticleRequest $request)
    {
        if(Auth::user()->isAuthor() || Auth::user()->isEditor())
        {
            $article = Article::create($request->all());
            $article->user()->associate(Auth::user());
            $article->save();
            \Session::flash('flash_message', 'Your article has been created!');
            return redirect('articles');
        }
        else
            return view('auth.login');
    }
    public function destroy(Article $article)
    {
    }
}
