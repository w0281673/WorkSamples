@extends('app')

@section('content')
    <h1>Edit: {!! $webPage->title !!}</h1>

    {!! Form::model($webPage, ['method' => 'PATCH', 'action' => ['WebPagesController@update', $webPage->id]]) !!}
        @include ('webpages/form', ['submitButtonText' => 'Update Page'])
    {!! Form::close() !!}

@stop