@extends('app')

@section('content')
    <h1>Name:{{$contentArea->description}}</h1>

    @foreach ($articles as $article)

        <article>

            <h2>
                {{$article->title}}
            </h2>

            <p>
                {!! $article->body !!}
            </p>

        </article>
    @endforeach
    {{App\User::find($contentArea->user_id)->first_name ." ". App\User::find($contentArea->user_id)->last_name}}<br>
    {{$contentArea->created_at}}<br>
    {{$contentArea->modified_by}}<br>
    {{$contentArea->updated_at}}<br>

@stop
