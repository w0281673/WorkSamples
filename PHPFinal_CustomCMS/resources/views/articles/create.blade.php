@extends('app')

@section('content')
    <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
    <script>tinymce.init({ selector:'textarea' });</script>
    <h1>Write a New Article</h1>

    <hr>

    {!! Form::model($article = new \App\Article, ['url' => 'articles']) !!}
        @include('articles/form', ['submitButtonText' => 'Add Article'])
    {!! Form::close() !!}

@stop