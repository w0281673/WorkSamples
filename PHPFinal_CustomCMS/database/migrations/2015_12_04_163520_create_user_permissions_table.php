<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUserPermissionsTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
        Schema::create('user_permissions', function(Blueprint $table)
        {
            $table->increments('upid');
            $table->integer('user_id')->references('id')->on('users');
            //$table->foreign('user_id');
            $table->integer('permission_id')->references('id')->on('permissions');
            //$table->foreign('permission_id');
        });
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('user_permissions');
	}

}
