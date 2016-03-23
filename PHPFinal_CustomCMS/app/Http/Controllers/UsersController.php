<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\User;
use App\Http\Requests\RegisterRequest;
use Auth;
use Illuminate\Http\Request;
use App\Http\Requests\UserRequest;

class UsersController extends Controller {

    public function index()
    {
        if(Auth::user()->isAdmin())
        {
            $users = User::all();
            return view('users.index', compact('users'));
        }
        else
            return view('auth.login');

    }
    public function show($id)
    {
        if(Auth::user()->isAdmin())
        {
            $user = User::find($id);
            return view('users.show', compact('user'));
        }
        else
            return view('auth.login');

    }
    public function create()
    {
        if(Auth::user()->isAdmin())
            return view('users.create');
        else
            return view('auth.login');
    }
    public function store(RegisterRequest $request)
    {
        if(Auth::user()->isAdmin())
        {
            $this->createUser($request);
            return redirect('users');
        }
        else
            return view('auth.login');

    }
    public function edit($id)
    {
        if(Auth::user()->isAdmin())
        {
            $user = User::find($id);
            return view('users.edit', compact('user'));
        }
        else
            return view('auth.login');

    }
    public function update($id, UserRequest $request)
    {
        if(Auth::user()->isAdmin())
        {
            $user = User::find($id);
            //not sure why this is giving errors. it works fine.
            $user->update([
                'first_name' => $request['first_name'],
                'last_name' => $request['last_name'],
                'email' => $request['email'],
                'password' => bcrypt($request['password']),
            ]);

            if(!$user->isValid($user->id))
            {
                return Redirect::back()->withInput()->withErrors($user->messages);
            }
            $permissions = $request['permissions'];
            if(!in_array("author", $permissions) && $user->isAuthor())
            {
                $user->permissions()->detach(1);
            }
            if(!in_array("editor", $permissions) && $user->isEditor())
            {
                $user->permissions()->detach(2);
            }
            if(!in_array("admin", $permissions) && $user->isAdmin())
            {
                $user->permissions()->detach(3);
            }
            foreach($permissions as $permission)
            {
                $user->givePermission($permission);
            }
            $user->modified_by = Auth::user()->first_name . ' ' . Auth::user()->last_name;
            $user->save();
            return redirect('users');
        }
        else
            return view('auth.login');

    }

    public function createUser(RegisterRequest $request)
    {
        if(Auth::user()->isAdmin())
        {
            $user = User::create([
                'first_name' => $request['first_name'],
                'last_name' => $request['last_name'],
                'email' => $request['email'],
                'password' => bcrypt($request['password']),
            ]);
            $permissions = $request['permissions'];
            if(!empty($permissions))
            {
                foreach($permissions as $permission)
                {
                    $user->givePermission($permission);
                }
            }
            $user->parent()->associate(Auth::user());
            $user->save();
        }
        else
            return view('auth.login');

    }
    public function destroy(User $user)
    {
        if(Auth::user()->isAdmin())
        {
            $user->delete();
            return redirect('users');
        }
        else
            return vieW('auth.login');

    }
}
