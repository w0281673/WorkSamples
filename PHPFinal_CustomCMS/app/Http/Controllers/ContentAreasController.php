<?php namespace App\Http\Controllers;

use App\Article;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\ContentArea;
use Auth;
use App\Http\Requests\ContentAreaRequest;
use Illuminate\Http\Request;

class ContentAreasController extends Controller {

    public function index()
    {
        if(Auth::check() && (Auth::user()->isEditor() || Auth::user()->isAdmin()))
        {
            $contentAreas = ContentArea::all();

            return view('contentareas.index', compact('contentAreas'));
        }
        else
            return view('auth/login');
    }
    public function show($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $articles = Article::all();
            $contentArea = ContentArea::find($id);
            return view('contentareas.show', compact('contentArea', 'articles'));
        }
        else
            return view('auth.login');
    }
    public function create()
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            return view('contentareas.create');
        }
        else
            return view('auth.login');
    }
    public function store(ContentAreaRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $this->createContentArea($request);
            return redirect('contentareas');
        }
        else
            return view('auth.login');

    }
    public function edit($id)
    {
        if (Auth::check() && Auth::user()->isEditor())
        {
            $contentArea = ContentArea::find($id);
            $contentArea->modified_by = Auth::user()->first_name . " " . Auth::user()->last_name;
            $contentArea->save();
            return view('contentareas.edit', compact('contentArea'));
        }
        else
            return view('auth.login');
    }
    public function update($id, ContentAreaRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $contentArea = ContentArea::find($id);
            //works
            $contentArea->update($request->all());
            \Session::flash('flash_message', 'Your content area has been created!');
            return redirect('contentareas');
        }
        else
            return view('auth.login');

    }
    public function createContentArea(ContentAreaRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $contentArea = ContentArea::create($request->all());
            $contentArea->user()->associate(Auth::user());
            $contentArea->save();
            \Session::flash('flash_message', 'Your content area has been created successfully!');
            return redirect('contentareas');
        }
        else
            return view('auth.login');
    }
    public function destroy($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $affectedRows = ContentArea::where('id', '=', $id)->delete();
            $articles = Article::where('content_area', '=', $id)->get();
            foreach($articles as $article)
            {
                $article->content_area = 0;
                $article->save();
            }
            return $affectedRows;
        }
        else
            return view('auth.login');
    }
}
