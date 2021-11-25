# spring-security-concepts
This Repo Contains Examples and concepts for spring-security


Setting Up Spring Security

	1) Add Spring-boot-starter-security to the classpath 
	    When this gets added spring adds a delegating proxy filter which intercepts all the requests before sending it to different servlets to handle the request.
	
	Adds A default Login Form and sets up default username and password
	
	
	2) Create a class that extends WebSecurityConfigurerAdapter
	  ? Extends WebSecurityConfigurerAdapter
	 
	Implement Authentication Method
	 configure(AuthenticationManagerBuilder authBuilder)
	    for JPA
	        create a class that implements UserDetailsService interface with method UserDetail loadByUsername(String username)
	Use JPA findByUserName() to retrieve the user as Optional<User> using jpa findByUsername(String username)
	Using this user create the Implementtaion of UserDetails which will act as the principal Object after successful Login.
	
	
	
	3) For Authorization  Implement another method
	 configure(HttpSecurity httpSecurity)
	httpSecurity.authorizeRequests().
	  antMatchers("/**").hasRole(String role)
	
	
Get passwordEncoder setup 


            Authentication

AuthenticationProvider

           

User
Entered
Credentials

AuthenticationManager

AuthenticationFilter

UserDetails
Service

Password
Encoder

        SecurityContext
![image](https://user-images.githubusercontent.com/31301292/143436227-00aca940-c593-49b0-8612-670846dc9514.png)

User Entered Credentials. Request First goes to AuthenticationFilter. AuthenticationFilter Converts it to a type of Authentication.

AuthenticationFilter consists of a method attemptAuthentication(HttpServletRequest request, HttpServletResponse response) which converts the incoming http requests to Authentication Object.  

AuthenticationFilter calls AuthenticationManager with Authentication Object .

Now AuthenticationManager searches for all the AuthenticationProvider implementations till the user is 
Authenticated successfully else all the authentication providers has been tested and then throws an exception.

AuthenticationProvider by default delegates the logic of authentication to UserDetailsService and Password Encoder.

After the successful Authentication, SecurityContext has been set with the current Authentication Object.![image](https://user-images.githubusercontent.com/31301292/143436271-e3b8ad47-975b-4609-bbd4-c0a6247814f4.png)
