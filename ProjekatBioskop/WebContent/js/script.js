
var MoviesManager = {
		
		getAll : function() {
			$.get('MoviesServlet', function(data) {
				$('#moviesTable1').DataTable({
					"paging": false,
					"info": false,
					"searching": false
					});
				for(m in data.movies) {
					$('#moviesTable1').dataTable().fnAddData ([
						data.movies[m].title,
						data.movies[m].duration,
						data.movies[m].distributor,
						data.movies[m].originCountry,
						data.movies[m].yearOfProduction	
					]);
				}
				
			});
		},

		searchMovies : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieInput = $('#searchMovieInput').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'searchMovieInput': searchMovieInput
			};
			$('#moviesTable1').dataTable().fnClearTable();
			$.get('SearchMoviesServlet', params, function(data) {
				for(m in data.filteredMovies) {
					$('#moviesTable1').dataTable().fnAddData ([
						data.filteredMovies[m].title,
						data.filteredMovies[m].duration,
						data.filteredMovies[m].distributor,
						data.filteredMovies[m].originCountry,
						data.filteredMovies[m].yearOfProduction	
						]);
					
				}
			});
			
			
		}
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
	
	$('#searchMovieBtn').click(function(e) {
		e.preventDefault();
		MoviesManager.searchMovies();
	});
	
	$('#btnConfirmLogin').click(function(e) {
		e.preventDefault();
		MoviesManager.searchMovies();
		console.log('login');
	});
	
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