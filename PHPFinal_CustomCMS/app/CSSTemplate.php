<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CSSTemplate extends Model {

    protected $table = 'templates';
    protected $fillable = [
        'name',
        'description',
        'active_state',
        'body'
    ];

    public function user()
    {
        return $this->belongsTo('App\User');
    }

}
