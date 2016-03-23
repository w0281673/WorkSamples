@extends('app')

@section('content')
    <meta name="csrf-token" content="{{ csrf_token() }}"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/deleteButton.js"></script>
    <h1>Content Areas</h1>
    <header><a href="{{ url('/contentareas/create') }}">New Content Area</a></header>
    @foreach ($contentAreas as $contentArea)

        <article>
            <h2>
                <a href="{{action('ContentAreasController@show', [$contentArea->id]) }}">{{$contentArea->name}}</a>
                @if(Auth::check() && Auth::user()->isEditor())
                    <a href="{{ url('/contentareas/'.$contentArea->id.'/edit') }}">Edit</a>
                    <a href="javascript:deleteContentArea('{{$contentArea->id}}');">Delete</a>
                @endif
            </h2>

            <div class="description">{{$contentArea->description}}</div>
        </article>
    @endforeach

@stop