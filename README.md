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
