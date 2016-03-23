@extends('app')

@section('content')

    <h1>Name:{{$cssTemplate->name}}</h1>

    <article>
        Body:{{$cssTemplate->body}}
    </article>
    Created by:{{App\User::find($cssTemplate->user_id)->first_name}}{{App\User::find($cssTemplate->user_id)->last_name}}
    Created at:{{$cssTemplate->created_at}}

    Modified by:{{$cssTemplate->modified_by}}
    Modified at:{{$cssTemplate->updated_at}}

@stop