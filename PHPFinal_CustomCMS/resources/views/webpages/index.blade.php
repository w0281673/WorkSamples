@extends('app')

@section('content')
    <meta name="csrf-token" content="{{ csrf_token() }}"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/deleteButton.js"></script>
    <h1>Web Pages</h1>
    <header><a href="{{ url('/webpages/create') }}">New Page</a></header>
    @foreach ($webPages as $webPage)

        <article>
            <h2>
                <a href="{{action('WebPagesController@show', [$webPage->alias]) }}">{{$webPage->name}}</a>
                @if(Auth::check() && Auth::user()->isEditor())
                    <a href="{{ url('/webpages/'.$webPage->id.'/edit') }}">Edit</a>
                    <a href="javascript:deleteWebPage('{{$webPage->id}}');">Delete</a>
                @endif
            </h2>

            <div class="description">{{$webPage->description}}</div>
        </article>
    @endforeach

@stop