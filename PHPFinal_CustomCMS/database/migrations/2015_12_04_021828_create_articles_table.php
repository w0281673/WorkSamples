<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateArticlesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('articles', function(Blueprint $table)
		{
			$table->increments('id');
            $table->string('name');
            $table->string('title');
            $table->string('description')->nullable();
            $table->text('body');
            $table->string('webpage')->nullable();
            //$table->foreign('webpage');
            $table->string('content_area')->nullable();
            //$table->foreign('content_area');
            $table->integer('user_id')->references('id')->on('users')->nullable();
            //$table->foreign('user_id');
            $table->string('modified_by')->nullable();
			$table->timestamps();
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('articles');
	}

}
