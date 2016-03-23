@extends('app')

@section('content')
    @if (count($errors) > 0)
        <div class="alert alert-danger">
            <strong>Whoops!</strong> There were some problems with your input.<br><br>
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif
    <h1>Make a New Content Area</h1>

    <hr>


    {!! Form::model($contentArea = new \App\ContentArea, ['url' => 'contentareas']) !!}
        @include('contentareas/form', ['submitButtonText' => 'Add Content Area'])
    {!! Form::close() !!}

@stop