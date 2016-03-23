<?php namespace App;

use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;
class Article extends Model {

	protected $fillable = [
        'name',
        'title',
        'description',
        'webpage',
        'body',
        'content_area',
        'created_by'
    ];

    public function scopeCreatedOn($query)
    {
        $query->where('created_on', '<=', Carbon::now());
    }

    public function scopeNotCreatedOn($query)
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
    public function contentAreas()
    {
        return $this->belongsTo('App\ContentArea');
    }
    public function webPages()
    {
        return $this->belongsToMany('App\WebPage');
    }
    public function getContentAreaListAttribute()
    {
        return $this->contentAreas->lists('id');
    }
}
