@extends('app')

@section('content')

    <h1>Articles</h1>
    <header><a href="{{ url('/articles/create') }}">New Article</a></header>

    @foreach ($articles as $article)

        <article>
            <h2>
                <a href="{{action('ArticlesController@show', [$article->id]) }}">{{$article->title}}</a>
                @if(Auth::check() && Auth::user()->isEditor())
                    <a href="{{ url('/articles/'.$article->id.'/edit') }}">Edit</a>
                @endif
            </h2>

            <div class="description">{{$article->description}}</div>
        </article>
    @endforeach

@stop