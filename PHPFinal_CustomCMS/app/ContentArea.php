<?php namespace App;

use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;
class ContentArea extends Model {

	protected $fillable = [
        'name',
        'description',
        'alias',
        'display_order',
        'webpage',

    ];


    public function scopeCreated($query)
    {
        $query->where('created_on', '<=', Carbon::now());
    }
    public function scopeNotCreated($query)
    {
        $query->where('created_on', '<=', Carbon::now());
    }
    public function setCreatedOnAttribute($date)
    {
        $this->attributes['created_on'] = Carbon::parse($date);
    }
    public function getCreatedOnAttribute($date)
    {
        return Carbon::parse($date)->format('Y-m-d');
    }
    public function user()
    {
        return $this->belongsTo('App\User');
    }
    public function articles()
    {
        return $this->hasMany('App\Article');
    }
}
