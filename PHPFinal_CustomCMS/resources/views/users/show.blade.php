@extends('app')

@section('content')

    <h1>Name:{{$user->first_name . ' ' . $user->last_name}}</h1>
    <h3>Email:{{$user->email}}</h3>
    <ul>
        @foreach($user->permissions as $permission)
        <li>{{$permission->name}}</li>
        @endforeach
    </ul>
    @if($user->user_id)
        Created by:{{App\User::find($user->user_id)->first_name}}{{App\User::find($user->user_id)->last_name}}<br>
    @else
        Created by:Admin<br>
    @endif
    Created at:{{$user->created_at}}<br>
    Modified by:{{$user->modified_by}}<br>
    Modified at:{{$user->updated_at}}<br>

@stop