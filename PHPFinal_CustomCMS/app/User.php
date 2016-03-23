<?php namespace App;

use Illuminate\Auth\Authenticatable;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Auth\Passwords\CanResetPassword;
use Illuminate\Contracts\Auth\Authenticatable as AuthenticatableContract;
use Illuminate\Contracts\Auth\CanResetPassword as CanResetPasswordContract;
use Validator;
class User extends Model implements AuthenticatableContract, CanResetPasswordContract {

	use Authenticatable, CanResetPassword;

	/**
	 * The database table used by the model.
	 *
	 * @var string
	 */
	protected $table = 'users';

	/**
	 * The attributes that are mass assignable.
	 *
	 * @var array
	 */
	protected $fillable = ['first_name', 'last_name', 'email', 'password', 'created_by', 'modified_by'];

	/**
	 * The attributes excluded from the model's JSON form.
	 *
	 * @var array
	 */
	protected $hidden = ['password', 'remember_token'];

    public function parent()
    {
        return $this->belongsTo('App\User', 'user_id');
    }
    public function children()
    {
        return $this->hasMany('App\User', 'user_id');
    }
    public function articles()
    {
        return $this->hasMany('App\Article');
    }
    public function contentAreas()
    {
        return $this->hasMany('App\ContentArea');
    }
    public function cssTemplates()
    {
        return $this->hasMany('App\CSSTemplate');
    }
    public function webPages()
    {
        return $this->hasMany('App\WebPage');
    }
    public function permissions()
    {
        return $this->belongsToMany('App\Permission', 'user_permissions');
    }
    public function isAuthor()
    {
        foreach ($this->permissions as $userPermission)
        {
            $uperm = $userPermission->pivot->permission_id;
            if($userPermission->pivot->permission_id == 1)
            {
                return true;
            }
        }

        return false;
    }
    public function isEditor()
    {
        foreach($this->permissions as $userPermission)
        {
            if($userPermission->pivot->permission_id == 2)
            {
                return true;
            }
        }

        return false;
    }
    public function isAdmin()
    {
        foreach($this->permissions as $userPermission)
        {
            if($userPermission->pivot->permission_id == 3)
            {
                return true;
            }
        }

        return false;
    }
    public function givePermission($perm)
    {
        if ($perm == 'author' && !$this->isAuthor())
        {
            $this->permissions()->attach(1);
        }
        elseif ($perm == 'editor' && !$this->isEditor())
        {
            $this->permissions()->attach(2);
        }
        elseif ($perm == 'admin' && !$this->isAdmin())
        {
            $this->permissions()->attach(3);
        }
    }

    public function isValid($id = null)
    {
        if(!is_null($id))
        {
            $rules = array(
                'email' => 'required|email|unique:users,email,'.$id,
                'password' => 'required',
            );
        }

        $validation = Validator::make($this->attributes, $rules);

        if($validation->passes())
        {
            return true;
        }
        $this->messages = $validation->messages();
        return false;
    }
}
