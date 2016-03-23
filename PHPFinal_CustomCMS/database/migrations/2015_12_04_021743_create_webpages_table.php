<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateWebpagesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('web_pages', function(Blueprint $table)
		{
			$table->increments('id');
            $table->string('name');
            $table->string('alias');
            $table->string('description')->nullable();
            $table->integer('user_id')->references('id')->on('user')->nullable();
            //$table->foreign('created_by');
            $table->string('modified_by')->nullable();
            //$table->foreign('modified_by');
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
		Schema::drop('web_pages');
	}

}
