var MoviesManager = {
//		
//		
//		
		getAll : function() {
			$.get('MoviesServlet', function(data) {
				$('#moviesTable1').DataTable({
					"paging": false,
					"info": false,
					"searching": false
				});
				for(m in data.movies) {
//					table.body().append(
//						'<tr>' +
//							'<td>'+ data.movies[m].title + '</td>' +
//							'<td>'+ data.movies[m].duration + '</td>' +
//							'<td>'+ data.movies[m].distributor + '</td>' +
//							'<td>'+ data.movies[m].originCountry + '</td>' +
//							'<td>'+ data.movies[m].yearOfProduction + '</td>' +
//						'</tr>'
//					);
					$('#moviesTable1').dataTable().fnAddData ([
						data.movies[m].title,
						data.movies[m].duration,
						data.movies[m].distributor,
						data.movies[m].originCountry,
						data.movies[m].yearOfProduction	
					]);
				}
				
			});
		}
//		
}





var StateManager = {
		
		initState : function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesTable').hide();
		},
		
		loggingState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').show();
			$('#RegisterForm').hide();
			$('#moviesTable').hide();
		},
		
		registerState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').show();
			$('#moviesTable').hide();
		},
		
		moviesState: function() {
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesTable').show();
			
		}
		
		

}




$(document).ready(function() {
	
	StateManager.initState();
	
	
	
	MoviesManager.getAll();
	
	
	
	$('#LoginBtn').click(function(e) {
		e.preventDefault();
		StateManager.loggingState();
	});
	
	$('#MoviesBtn').click(function (e) {
		e.preventDefault();
		StateManager.moviesState();
	});
	
	$('#RegisterBtn').click(function(e) {
		e.preventDefault();
		StateManager.registerState();
	});
	
	
	
	
});