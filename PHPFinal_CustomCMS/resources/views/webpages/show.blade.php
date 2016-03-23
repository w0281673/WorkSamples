@extends('app')

@section('content')

    <title>{{$webPage->name}}</title>

    <style type="text/css">
        @if($cssTemplate)
            {{$cssTemplate->body}}
        @endif
    </style>

    <h1>{{$webPage->name}}</h1>
    <header>{{$webPage->description}}</header>

    @if(Auth::check() && Auth::user()->isAuthor())
        <header><a href="{{ url('/articles/create') }}">New Article</a></header>
    @endif
    @foreach ($contentAreas as $content)
        <div id={{$content->alias}}>

            <hr>

            <h2>{{$content->name}}</h2>
            <h3>{{$content->description}}</h3>

            <hr>

            @foreach ($articles as $article)
                @if(($article->content_area == $content->alias) && (($article->webpage == $webPage->alias) || ($article->webpage == 'all')))

                    <h4>{{$article->name}}
                        @if(Auth::check() && Auth::user()->isAuthor())
                            <a href="{{ url('/articles/'.$article->id.'/edit') }}">Edit</a>
                        @endif
                    </h4>

                    <h5>{{$article->description}}</h5>

                    <p>{!! $article->body !!}</p>

                    <footer>
                        Article by {{App\User::find($article->user_id)->first_name . " " . App\User::find($article->user_id)->last_name}}<br>
                        {{$article->created_at}}<br>
                        Last Modified by {{$article->modified_by}}<br>
                        {{$article->updated_at}}<br>
                    </footer>

                @endif
            @endforeach
        </div>
    @endforeach
    <hr>
    <footer>
        Page created by {{App\User::find($webPage->user_id)->first_name . " " . App\User::find($webPage->user_id)->last_name}}<br>
        {{$webPage->created_at}}<br>
        Page last modified by {{$webPage->modified_by}}<br>
        {{$webPage->updated_at}}<br>
    </footer>
@stop
