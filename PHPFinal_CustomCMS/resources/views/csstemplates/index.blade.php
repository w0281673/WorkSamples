@extends('app')

@section('content')
    <meta name="csrf-token" content="{{ csrf_token() }}"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/deleteButton.js"></script>
    <h1>CSS Templates</h1>
    <header><a href="{{ url('/csstemplates/create') }}">New Template</a></header>
    @foreach ($cssTemplates as $template)

        <article>
            <h2>
                <a href="{{action('CSSTemplatesController@show', [$template->id]) }}">{{$template->name}}</a>
                @if(Auth::check() && Auth::user()->isEditor())
                    <a href="{{ url('/csstemplates/'.$template->id.'/edit') }}">Edit</a>
                    <a href="javascript:deleteTemplate('{{$template->id}}');">Delete</a>
                @endif
            </h2>

            <div class="description">{{$template->description}}</div>
        </article>
    @endforeach

@stop