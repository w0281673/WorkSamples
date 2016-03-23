@extends('app')

@section('content')

    <h1>{{$article->title}}</h1>
    <hr>
    <article>
        {!! $article->body !!}
    </article>
    <hr>
        Created by:{{App\User::find($article->user_id)->first_name}}{{App\User::find($article->user_id)->last_name}}<br>
        Created at:{{$article->created_at}}<br>
        Modified by:{{$article->modified_by}}<br>
        Modified at:{{$article->updated_at}}<br>

@stop