function validate_form ( )
{
	valid = true;

        if ( document.LoginForm.login.value == "" )
        {
                alert ( "Пожалуйста, введите Ваше имя пользователя" );
                valid = false;
                
        }
        if ( document.LoginForm.password.value == "" )
        {
                alert ( "Пожалуйста, введите Ваш пароль" );
                valid = false;
        }

        return valid;
}


