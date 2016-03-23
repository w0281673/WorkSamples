@extends('app')

@section('content')
		
		<link href='//fonts.googleapis.com/css?family=Lato:100' rel='stylesheet' type='text/css'>

		<style>
			body {
				margin: 0;
				padding: 0;
				width: 100%;
				height: 100%;
				color: #B0BEC5;
				display: table;
				font-weight: 100;
				font-family: 'Lato';
			}

			.container {
				text-align: center;
                margin-top: 20px;
				display: block;
				vertical-align: middle;
			}

			.content {
				text-align: center;
				display: inline-block;
			}

			.title {
				font-size: 84px;
				margin-bottom: 40px;
			}

			.quote {
				font-size: 24px;
			}
            header {
                font-size: 24px;
            }
		</style>
        <header>Latest</header>
		<div class="container">
			<div class="content">
                @if($latest)
				<div class="title">{{$latest->name}}</div>
				<div class="quote">{!! $latest->body !!}</div>
			    @endif
            </div>
		</div>


@stop