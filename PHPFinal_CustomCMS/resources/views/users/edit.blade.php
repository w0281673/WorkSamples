@extends('app')

@section('content')

    <h1>Edit: {!! $user->first_name !!}</h1>

    {!! Form::model($user, ['method' => 'PATCH', 'action' => ['UsersController@update', $user->id]]) !!}
        @include ('users/form', ['submitButtonText' => 'Update User'])
    {!! Form::close() !!}

@stop