@extends('app')

@section('content')
    <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
    <script>tinymce.init({ selector:'textarea' });</script>
    <h1>Edit: {!! $article->title !!}</h1>

    {!! Form::model($article, ['method' => 'PATCH', 'action' => ['ArticlesController@update', $article->id]]) !!}
    @include ('articles/form', ['submitButtonText' => 'Update Article'])
    {!! Form::close() !!}

@stop