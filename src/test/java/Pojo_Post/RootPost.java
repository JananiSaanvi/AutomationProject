package Pojo_Post;

public class RootPost {
	
	   private String username;
	    private String email;
	    private String password;
		public RootPost(String username, String email, String password) {
			super();
			this.username = username;
			this.email = email;
			this.password = password;
		}
		public String getUsername() {
			return username;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

	

}
