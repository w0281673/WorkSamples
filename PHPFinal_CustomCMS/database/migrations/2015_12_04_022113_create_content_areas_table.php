<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateContentAreasTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('content_areas', function(Blueprint $table)
		{
			$table->increments('id');
            $table->string('name');
            $table->string('alias');
            $table->integer('display_order')->unique();
            //$table->foreign('webpage');
            $table->string('description');
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
		Schema::drop('content_areas');
	}

}
