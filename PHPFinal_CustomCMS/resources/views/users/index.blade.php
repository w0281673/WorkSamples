@extends('app')

@section('content')

    <h1>Users</h1>
    <header><a href="{{ url('/users/create') }}">New User</a></header>
    @foreach ($users as $user)

        <article>

            <h2>
                <a href="{{action('UsersController@show', [$user->id]) }}">{{$user->first_name." ".$user->last_name}}</a>
                <button type="submit" class="btn btn-success">
                    <a href="{{ url('/users/'.$user->id.'/edit') }}">Edit</a>
                </button>
            </h2>

        </article>
    @endforeach

@stop