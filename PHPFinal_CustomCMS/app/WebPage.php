<?php namespace App;

use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;
class WebPage extends Model {

    protected $fillable = [
        'name',
        'alias',
        'description',
        'created_by',
        'modified_by'
    ];

    public function user()
    {
        return $this->belongsTo('App\User');
    }

    public function articles()
    {
        return $this->hasMany('App\Article');
    }

    public function contentAreas()
    {
        return $this->hasMany('App\ContentArea');
    }
}