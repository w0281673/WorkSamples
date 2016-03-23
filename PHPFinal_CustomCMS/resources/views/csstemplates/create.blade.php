@extends('app')

@section('content')
    <h1>Make a new CSS Template</h1>

    <hr>

    {!! Form::model($template = new \App\CSSTemplate, ['url' => 'csstemplates']) !!}
    @include('csstemplates/form', ['submitButtonText' => 'Add Template'])
    {!! Form::close() !!}

@stop