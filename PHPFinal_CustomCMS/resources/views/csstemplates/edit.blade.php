@extends('app')

@section('content')
    <h1>Edit: {!! $cssTemplate->title !!}</h1>

    {!! Form::model($cssTemplate, ['method' => 'PATCH', 'action' => ['CSSTemplatesController@update', $cssTemplate->id]]) !!}
        @include ('csstemplates/form', ['submitButtonText' => 'Update Article'])
    {!! Form::close() !!}

@stop