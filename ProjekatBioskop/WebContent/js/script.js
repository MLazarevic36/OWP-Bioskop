
var MoviesManager = {
		
		getAll : function() {
			$.get('MoviesServlet', function(data) {
				$('#moviesTable1').DataTable({
					data: data.movies,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
						{
							"data" : 'title',
							render:function(data, type, row){
								return '<a id="movieRedirect" href="#" data-id="' + row.id + '">' + data + '</a>';
							}
						},
						{data : 'duration'},
						{data : 'distributor'},
						{data : 'originCountry'},
						{data : 'yearOfProduction'},
						{
							"data": null,
							render:function(data, type, row)
							{
								
								return '<button id="deleteMovie" data-id="' + data.id + '">Delete</button>'
								
							},
							"targets": -1
						}
					]
				})
	
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
					$('#moviesTable1').dataTable().fnDestroy();
					$('#moviesTable1').DataTable({
						data: data.filteredMovies,
						paging: false,
						info: false,
						searching: false,
						autoWidth: true,
						columns: [
							{
								"data" : 'title',
								render:function(data, type, row){
									return '<a id="movieRedirect" href="" data-id="' + row.id + '">' + data + '</a>';
								}
							},
							{data : 'duration'},
							{data : 'distributor'},
							{data : 'originCountry'},
							{data : 'yearOfProduction'},
							{
								"data": null,
								render:function(data, type, row)
								{
									
									return '<button id="deleteMovie" data-id="' + data.id + '">Delete</button>'
									
								},
								"targets": -1
							}
						]
					})
					
				}
			});
		},
		
		addMovie : function() {
			var title = $('#newMovieTitle').val();
			var duration = $('#newMovieDuration').val();
			var distributor = $('#newMovieDistributor').val();
			var originCountry = $('#newMovieOriginCountry').val();
			var yearOfProduction = $('#newMovieYearOfProduction').val();
			params = {
					'action': 'add',
					'title': title,
					'duration': duration,
					'distributor': distributor,
					'originCountry': originCountry,
					'yearOfProduction': yearOfProduction
			};
			$.post('MoviesServlet', params, function(data) {
				console.log(data);
			});
			
		},
		
		deleteMovie : function(ID) {
			params = {
					'action': 'delete',
					'id': ID
			};
			$.post('MoviesServlet', params, function(data){
				console.log(data);
			});
			
		},
		
		getMovie : function(ID) {
			params = {
					'action': 'getMovie',
					'id': ID
			};
			$.post('MoviesServlet', params, function(data){
				$("#updatedMovieTitle").val(data.movie.title);
				$("#updatedMovieDuration").val(data.movie.duration);
				$("#updatedMovieDistributor").val(data.movie.distributor);
				$("#updatedMovieOriginCountry").val(data.movie.originCountry);
				$("#updatedMovieYearOfProduction").val(data.movie.yearOfProduction);
				$('#movieTitle').append('Title: ' + data.movie.title);
				$('#movieDuration').append('Duration: ' + data.movie.duration);
				$('#movieDistributor').append('Distributor: ' + data.movie.distributor);
				$('#movieOriginCountry').append('Origin country: ' + data.movie.originCountry);
				$('#movieYearOfProduction').append('Year of production: ' + data.movie.yearOfProduction);
				
			} );
			$('#movieDetail').show();
			$('#movieCommands').show();
			$('#updateMovieForm').hide();
			$('#moviesSection').hide();
			
		},
		
		updateMovie : function(ID) {
			var title = $("#updatedMovieTitle").val();
			var duration = $("#updatedMovieDuration").val();
			var distributor = $("#updatedMovieDistributor").val();
			var originCountry = $("#updatedMovieOriginCountry").val();
			var yearOfProduction = $("#updatedMovieYearOfProduction").val();
			
			params = {
					'action': 'update',
					'id': ID,
					'title': title,
					'duration': duration,
					'distributor': distributor,
					'originCountry': originCountry,
					'yearOfProduction': yearOfProduction
			};
			$.post('MoviesServlet', params, function(data) {
				$("#updatedMovieTitle").val(data.movie.title);
				$("#updatedMovieDuration").val(data.movie.duration);
				$("#updatedMovieDistributor").val(data.movie.distributor);
				$("#updatedMovieOriginCountry").val(data.movie.originCountry);
				$("#updatedMovieYearOfProduction").val(data.movie.yearOfProduction);
				$('#movieTitle').append('Title: ' + data.movie.title);
				$('#movieDuration').append('Duration: ' + data.movie.duration);
				$('#movieDistributor').append('Distributor: ' + data.movie.distributor);
				$('#movieOriginCountry').append('Origin country: ' + data.movie.originCountry);
				$('#movieYearOfProduction').append('Year of production: ' + data.movie.yearOfProduction);
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
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
		},
		
		loggingState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').show();
			$('#RegisterForm').hide();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
		},
		
		registerState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').show();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
		},
		
		moviesState: function() {
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesSection').show();
			$('#addMovieForm').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			
		}
		
}




$(document).ready(function() {
	
	StateManager.initState();
	
	MoviesManager.getAll();
	
	$('#searchMovieBtn').click(function(e) {
		e.preventDefault();
		MoviesManager.searchMovies();
	});
	
	$('#addNewMovie').click(function(e) {
		$('#addMovieForm').toggle();
	});
	
	$('#submitNewMovie').click(function(e) {
		e.preventDefault();
		MoviesManager.addMovie();
		$('#addMovieForm').toggle();
		$('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
		
	})
	
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
	
	
    $('body').on('click', '#deleteMovie', function(e){
        var id= $(this).attr("data-id");
        MoviesManager.deleteMovie(id);
        $(this).parents("tr").remove();
        $('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
        
        
    });
    
    $('body').on('click', '#movieRedirect', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        $('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
        MoviesManager.getMovie(id);
      
        
    });
    
    $('#updateMovieFromDetail').click(function(e) {
		$('#updateMovieForm').toggle();
	});
    
    $('#deleteMovieFromDetail').click(function(e) {
    	var baseUrl = (window.location).href;
    	var id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
		MoviesManager.deleteMovie(id);
		$('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
        $('#movieDetail').hide();
		$('#movieCommands').hide();
		$('#moviesSection').show();
    });
    
    $('#updateMovie').click(function(e) {
    	var baseUrl = (window.location).href;
    	var id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
    	$('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
		MoviesManager.updateMovie(id);
		$('#updateMovieForm').hide();
		$('#moviesTable1').dataTable().fnDestroy();
		MoviesManager.getAll();
		
	});
	
	
	
	
});