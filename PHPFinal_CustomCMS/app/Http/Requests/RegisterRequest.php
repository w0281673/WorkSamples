<?php
/**
 * Created by PhpStorm.
 * User: inet2005
 * Date: 12/15/15
 * Time: 11:28 PM
 */

namespace App\Http\Requests;


class RegisterRequest extends Request{


    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'first_name' => 'required|min:2',
            'last_name' => 'required|min:2',
            'email' => 'required',
            'password' => 'required',

        ];
    }


}