@extends('app')

@section('content')
    <h1>Edit: {!! $contentArea->title !!}</h1>

    {!! Form::model($contentArea, ['method' => 'PATCH', 'action' => ['ContentAreasController@update', $contentArea->id]]) !!}
    @include ('contentareas/form', ['submitButtonText' => 'Update Content Area'])
    {!! Form::close() !!}

@stop