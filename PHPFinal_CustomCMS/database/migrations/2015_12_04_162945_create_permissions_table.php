<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePermissionsTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
        Schema::create('permissions', function(Blueprint $table)
        {
            $table->increments('id');
            $table->string('name');
        });
        DB::table('permissions')->insert(array('name' => 'author'));
        DB::table('permissions')->insert(array('name' => 'editor'));
        DB::table('permissions')->insert(array('name' => 'admin'));
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('permissions');
	}

}
