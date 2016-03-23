<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\CSSTemplate;
use App\Http\Controllers\Controller;
use App\Http\Requests\CSSTemplateRequest;
use Illuminate\Http\Request;
use Auth;

class CSSTemplatesController extends Controller {

    public function index()
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $cssTemplates = CSSTemplate::all();
            return view('csstemplates.index', compact('cssTemplates'));
        }
        else
            return view('auth.login');
    }
    public function show($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $cssTemplate = CSSTemplate::find($id);
            return view('csstemplates.show', compact('cssTemplate'));
        }
        else
            return view('auth.login');
    }
    public function create()
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            return view('csstemplates.create');
        }
        else
            return view('auth.login');

    }
    public function store(CSSTemplateRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            if($request->input('active_state') == 1)
            {
                $cssTemplates = CSSTemplate::all();
                foreach($cssTemplates as $cssTemplate)
                {
                    $cssTemplate->active_state = 0;
                    $cssTemplate->save();
                }
            }
            $this->createCSSTemplate($request);
            return redirect('csstemplates');
        }
        else
            return view('auth.login');

    }
    public function edit($id)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $cssTemplate = CSSTemplate::find($id);
            $cssTemplate->modified_by = Auth::user()->first_name . " " . Auth::user()->last_name;
            $cssTemplate->save();
            return view('csstemplates.edit', compact('cssTemplate'));
        }
        else
            return view('auth.login');

    }
    public function update($id, CSSTemplateRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $cssTemplate = CSSTemplate::find($id);
            if($cssTemplate->active_state = 1)
            {
                $cssTemplates = CSSTemplate::all();
                foreach($cssTemplates as $template)
                {
                    $template->active_state = 0;
                    $template->save();
                }
            }
            //works fine.
            $cssTemplate->update($request->all());
            \Session::flash('flash_message', 'Your template has been updated successfully!');
            return redirect('csstemplates');
        }
        else
            return view('auth.login');

    }
    public function createCSSTemplate(CSSTemplateRequest $request)
    {
        if(Auth::check() && Auth::user()->isEditor())
        {
            $cssTemplate = CSSTemplate::create($request->all());
            $cssTemplate->user()->associate(Auth::user());
            $cssTemplate->save();
            \Session::flash('flash_message', 'Your template has been created successfully!');
            return redirect('csstemplates');
        }
        else
            return view('auth.login');
    }
    public function destroy($id)
    {
        $affectedRows = CSSTemplate::where('id', '=', $id)->delete();
        return $affectedRows;
    }

}
