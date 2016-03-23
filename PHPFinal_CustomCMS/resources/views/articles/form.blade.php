<div class="form-group">
    {!! Form::label('name', 'Name:') !!}
    {!! Form::text('name', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('title', 'Title:') !!}
    {!! Form::text('title', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('description', 'Description:') !!}
    {!! Form::text('description', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('webpage', 'Page:') !!}
    <select name="webpage" id="webpage">
        @foreach($pageOptions as $pageOption)
            <option value="{{$pageOption}}">{{$pageOption}}</option>
        @endforeach
    </select>
</div>

<div class="form-group">
    {!! Form::label('body', 'Body:') !!}
    {!! Form::textarea('body', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('content_area', 'Content Area:') !!}
    <select name="content_area" id="content_area">
        @foreach($contentAreas as $content)
            <option value="{{$content}}">{{$content}}</option>
        @endforeach
    </select>
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary form-control']) !!}
</div>