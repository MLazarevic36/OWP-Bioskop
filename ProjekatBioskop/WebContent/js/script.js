var ProjectionsManager = {
		
		getAll : function(){
			$.get('ProjectionsServlet', function(data){
				$('#projectionsTable1').DataTable ({
					data: data.projections,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'movie',
							render:function(data, type, row){
								return '<a id="movieRedirectFromProjection" data-name="' + data + '" href="#">' + data + '</a>';
								}
							},
							{
								"data" : 'dateOutput',
								render:function(data, type, row){
									return '<a id="projectionRedirect" data-id="' + row.id +'" href="#">' + data + '</a>';
								}
									
							},
							{data : 'projectionType'},
							{data : 'theater'},
							{data : 'ticketPrice'}
						]
				})
			})
		},
		
		searchProjections : function() {
			var $searchMovieDropdown = $('#projectionMovieFilter').find('option:selected');
			var $searchProjectionTypeDropdown = $('#projectionTypeFilter').find('option:selected');
			var $searchProjectionTheaterDropdown = $('#projectionTheaterFilter').find('option:selected');
			var startDate = $('#dateFrom').val();
			var endDate = $('#dateTo').val();
			var minPrice = $('#priceFrom').val();
			var maxPrice = $('#priceTo').val();
			var movieTitle = $searchMovieDropdown.val();
			if (movieTitle === 'Select movie') {
				movieTitle = '';
			}
			var projectionType = $searchProjectionTypeDropdown.val();
			if (projectionType === 'Select projection type') {
				projectionType = '';
			}
			var theater = $searchProjectionTheaterDropdown.val();
			if (theater === 'Select theater') {
				theater = '';
			}
			var params = {
					
					'projectionMovieFilter': movieTitle,
					'projectionTypeFilter': projectionType,
					'projectionTheaterFilter': theater,
					'projectionStartDate': startDate,
					'projectionEndDate': endDate,
					'projectionMinPrice' : minPrice,
					'projectionMaxPrice' : maxPrice
			};
			$('#projectionsTable1').dataTable().fnDestroy();
			$.get('SearchProjectionsServlet', params, function(data){
				$('#projectionsTable1').DataTable ({
					data: data.filteredProjections,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'movie',
							render:function(data, type, row){
								return '<a id="movieRedirectFromProjection" data-name="' + data + '" href="#">' + data + '</a>';
								}
							},
							{
								"data" : 'dateOutput',
								render:function(data, type, row){
									return '<a id="projectionRedirect" data-id="' + row.id +'" href="#">' + data + '</a>';
								}
									
							},
							{data : 'projectionType'},
							{data : 'theater'},
							{data : 'ticketPrice'}
						]
				})
			})
		},
		
		populateMovieDropdown : function () {
			
			$.get('MoviesServlet', function(data){
				var $dropdown = $('#newProjectionMovie');
				var newData = data.movies;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.title));
				});
				var $dropdownFilter = $('#projectionMovieFilter');
				$.each(newData, function(){
					$dropdownFilter.append($("<option />").val(this.id).text(this.title));
				})
		});
			
		},
		
		populateProjectionTypeDrowpdown: function() {
			$.get('ProjectionTypeServlet', function(data){
				var $dropdown = $('#newProjectionType');
				var newData = data.projectionTypes;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.name));
				})
				var $dropdownFilter = $('#projectionTypeFilter');
				$.each(newData, function(){
					$dropdownFilter.append($("<option />").val(this.id).text(this.name));
				})
			})
			
		},
		
		populateTheaterDropdown: function () {
			$.get('TheaterServlet', function(data){
				var $dropdown = $('#newProjectionTheater');
				var newData = data.theaters;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.name));
				})
				var $dropdownFilter = $('#projectionTheaterFilter');
				$.each(newData, function(){
					$dropdownFilter.append($("<option />").val(this.id).text(this.name));
				})
			})
		},
		
		updateSeatProjection: function(ID, projection_id) {
			params = {
					'action': 'updateSeatProjection',
					'id': ID,
					'projection_id': projection_id
			};
			$.post('SeatServlet', params, function(data){
				console.log(params);
				console.log(data);
			})
		},
		
		populateSeatRadio: function(projection_id) {
			params = {
					'projection_id': projection_id
					
			};
			
			var proj_id = projection_id;
			
			$.get('SeatServlet', params, function(data) {
				for(i = 0; i < data.seats.length; i++){
					var newData = data.seats[i];
					if(newData.taken === 0 && newData.projection_id === proj_id){
					var radioBtn = $('<label for="seat"><input type="radio" name="seat" value="'+ newData.seat_id +'" />'+ newData.seat_id  +'</label>');
					radioBtn.appendTo('#radioSeats');
					}
				}
			})
		},
		
		addProjection : function() {
			var movie = $('#newProjectionMovie').val();
			var projectionType = $('#newProjectionType').val();
			var theater = $('#newProjectionTheater').val();
			var date = $('#newProjectionDate').val();
			var time = $('#newProjectionTime').val();
			var ticket = $('#newProjectionTicketPrice').val();
			params = {
					'action': 'add',
					'movie': movie,
					'projectionType': projectionType,
					'theater': theater,
					'date': date,
					'time': time,
					'ticket': ticket
			};
			$.post('ProjectionsServlet', params, function(data) {
				console.log(data);
			});
			
		},
		
		getProjection : function(ID) {
			params = {
					'id': ID
			};
			$.get('ProjectionServlet', params, function(data){
				$('#projectionTicketsTable1').dataTable().fnDestroy();
				TicketManager.getAllByProjection(data.projection.id);
				$('#hiddenProjectionDetailId').text();
				$('#projectionTitle').text('');
				$('#projectionDateAndTime').text('');
				$('#projectionType').text('');
				$('#projectionTheater').text('');
				$('#projectionTicketPrice').text('');
				$('#projectionTitle').append('Title: ' + '<a id="movieRedirectFromProjectionDetail" data-name="' + data.projection.movie + '" href="#">' + data.projection.movie + '</a>');
				$('#projectionDateAndTime').append('Date and time: ' + data.projection.dateOutput);
				$('#projectionType').append('Type: ' + data.projection.projectionType);
				$('#projectionTheater').append('Theater: ' + data.projection.theater);
				$('#projectionTicketPrice').append('Ticket price: ' + data.projection.ticketPrice);
				$('#hiddenProjectionDetailId').val(data.projection.id);
			} );
			
		},
		
		deleteProjectionLogic : function(ID) {
			params = {
				'action':'deleteProjectionLogic',
				'id': ID
			};
			$.post('ProjectionsServlet', params, function(data){
				window.location.reload();
			});
		}
}

var TicketManager = {
		
		populateTicketDetails: function(ID) {
			params = {
					'id': ID
			};
			$.get('ProjectionServlet', params, function(data){
				window.location.href = "#";
		        window.location.href += '?id=' + data.projection.id;
				$('#radioSeats').empty();
				ProjectionsManager.populateSeatRadio(data.projection.id);
				$('#ticketProjectionTitle').text('');
				$('#ticketProjectionDateAndTime').text('');
				$('#ticketProjectionType').text('');
				$('#ticketProjectionTheater').text('');
				$('#ticketProjectionTicketPrice').text('');
				$('#ticketProjectionTitle').append('Title: ' + '<a id="movieRedirectFromTicketDetail" data-name="' + data.projection.movie + '" href="#">' + data.projection.movie + '</a>');
				$('#ticketProjectionDateAndTime').append('Date and time: '+ '<a id="projectionRedirectFromTicketDetail" data-id="' + data.projection.id + '" href="#">' + data.projection.dateOutput + '</a>');
				$('#ticketProjectionType').append('Type: ' + data.projection.projectionType);
				$('#ticketProjectionTheater').append('Theater: ' + data.projection.theater);
				$('#ticketProjectionTicketPrice').append('Ticket price: ' + data.projection.ticketPrice);
			} );
			
		},
		
		purchaseTicket: function() {
			
			params_user = {
					'action':'loggedInUserId'
			}
			
			$.get('UseridServlet', params_user, function(data){
				var baseUrl = (window.location).href;
		    	var projection_id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
		    	var seat = $("#radioSeats input[type='radio']:checked").val();
				params_ticket = {
						'action':'addTicket',
						'projection': projection_id,
						'seat': seat,
						'buyer': data.loggedInUserId
				}
				$.post('TicketServlet', params_ticket, function(data) {
					alert('You have successfully bought ticket!')
					TicketManager.populateTicketDetails(projection_id);
				})
				
			})
			
			
		},
		
		getAll : function(ID) {
			params = {
					'id': ID 
			};
			$.get('TicketServlet', params, function(data){
				$('#ticketsTable1').DataTable ({
					data: data.tickets,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'dateOutput',
							render:function(data, type, row){
								return '<a id="ticketRedirectionFromTable" data-id="' + row.id + '" href="#">' + data + '</a>';
								}
							}
							
						]
				
				})
			})
		},
		
		getAllByProjection : function(ID) {
			params = {
					'id': ID 
			};
			$.get('ProjectionTicketsServlet', params, function(data){
				$('#projectionTicketsTable1').DataTable ({
					data: data.tickets,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'dateOutput',
							render:function(data, type, row){
								return '<a id="ticketRedirectionFromTable" data-id="' + row.id + '" href="#">' + data + '</a>';
								}
							},
							{
								"data": 'buyerName',
								render:function(data, type, row){
									return '<a id="userRedirectProjectionTicketsTable" data-name="' + data + '" href="#">' + data + '</a>';
									}
							}
							
						]
				
				})
			})
		},
		
		getSingleTicket : function(ID) {
			params = {
				'id' : ID
			};
			$.get('SingleTicketServlet', params, function(data) {
				$('#singleTicketMovie').text('');
				$('#singleTicketProjection').text('');
				$('#singleTicketSeat').text('');
				$('#singleTicketProjectionType').text('');
				$('#singleTicketTheater').text('');
				$('#singleTicketBuyer').text('');
				$('#singleTicketPrice').text('');
				$('#hiddenProjectionId').text('');
				$('#singleTicketMovie').append('Title: ' + '<a id="movieRedirectFromSingleTicket" data-name="' + data.singleTicket.movieTitle + '" href="#">' + data.singleTicket.movieTitle + '</a>');
				$('#singleTicketProjection').append('Date and time: '+ '<a id="projectionRedirectFromSingleTicket" data-id="' + data.singleTicket.dateOutputTimeOfProjection + '" href="#">' + data.singleTicket.dateOutputTimeOfProjection + '</a>');
				$('#singleTicketSeat').append('Seat: ' + data.singleTicket.seat);
				$('#singleTicketTheater').append('Theater: ' + data.singleTicket.theater);
				$('#singleTicketProjectionType').append('Projection type: ' + data.singleTicket.projectionType);
				$('#singleTicketBuyer').append('Buyer: ' + '<a id="userRedirectFromSingleTicket" data-name="' + data.singleTicket.buyer + '" href="#">' + data.singleTicket.buyer + '</a>');
				$('#singleTicketPrice').append('Price: ' + data.singleTicket.price);
				$('#hiddenProjectionId').val(data.singleTicket.projectionId);
			})
		},
		
		deleteTicket : function(ID) {
			params = {
					'action': 'deleteTicket',
					'id': ID
			};
			$.post('TicketServlet', params, function(data){
				console.log(data);
			});
			
		}
		

		
}

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
							"targets": -1,
							"visible": false
						}
					]
				})
	
			});
		},

		searchMovies : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieInput = $('#searchMovieInput').val();
			var searchMovieDurationFrom = $('#durationFrom').val();
			var searchMovieDurationTo = $('#durationTo').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'searchMovieInput': searchMovieInput,
					'durationMin': searchMovieDurationFrom,
					'durationMax': searchMovieDurationTo
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
								"targets": -1,
								"visible": false
							}
						]
					})
					
				}
			});
		},
		
		searchMoviesAdmin : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieInput = $('#searchMovieInput').val();
			var searchMovieDurationFrom = $('#durationFrom').val();
			var searchMovieDurationTo = $('#durationTo').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'searchMovieInput': searchMovieInput,
					'durationMin': searchMovieDurationFrom,
					'durationMax': searchMovieDurationTo
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
		
		searchMoviesByDuration : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieDurationFrom = $('#durationFrom').val();
			var searchMovieDurationTo = $('#durationTo').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'durationMin': searchMovieDurationFrom,
					'durationMax': searchMovieDurationTo
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
								"targets": -1,
								"visible": false
							}
						]
					})
					
				}
			});
		},
		
		searchMoviesByDurationAdmin : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieDurationFrom = $('#durationFrom').val();
			var searchMovieDurationTo = $('#durationTo').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'durationFrom': searchMovieDurationFrom,
					'durationTo': searchMovieDurationTo
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
				$('#movieTitle').text('');
				$('#movieDuration').text('');
				$('#movieDistributor').text('');
				$('#movieOriginCountry').text('');
				$('#movieYearOfProduction').text('');
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
			
			
		},
		
		getMovieByTitle: function(title) {
			params = {
				'action': 'getMovieByTitle',
				'title': title
			};
			$.post('MoviesServlet', params, function(data){
				window.location.href = "#";
		        window.location.href += '?id=' + data.movie.id;
		        $('#movieTitle').text('');
				$('#movieDuration').text('');
				$('#movieDistributor').text('');
				$('#movieOriginCountry').text('');
				$('#movieYearOfProduction').text('');
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
			})
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
		        $('#movieTitle').text('');
				$('#movieDuration').text('');
				$('#movieDistributor').text('');
				$('#movieOriginCountry').text('');
				$('#movieYearOfProduction').text('');
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

var UsersManager = {
		logIn : function() {
			var username = $('#LoginUsername').val();
			var password = $('#LoginPassword').val();
			
			params = {
					'username': username,
					'password': password
			};
			
			$.post('LoginServlet', params, function(data){
				if(data.status === 'success') {
					StateManager.initState();
				}
				if(data.status === 'failure')
					$('#LoginUsername').val('');
					$('#LoginPassword').val('');
				$('#LoginForm').append('<p style="color:red">Failed login attempt</p>');
			});
		},
		
		logOut : function() {
			$.get('LogoutServlet', function(data) {
				console.log(data);
			})
		},
		
		getAll : function() {
			$.get('UsersServlet', function(data){
				$('#usersTable1').DataTable ({
					data: data.users,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'username',
							render:function(data, type, row){
								return '<a id="userRedirectFromTable" data-id="' + row.id + '" href="#">' + data + '</a>';
								}
							},
							{data : 'dateOutput'},
							{data : 'role'}
						]
				})
			})
		},
		
		populateUserProfile : function(ID) {
			params = {
					'user_id' : ID
			};
			$.get('SingleUserServlet', params, function(data){
				var baseUrl = (window.location).href;
		    	var user_id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
				TicketManager.getAll(user_id);
				$('#hiddenUserId').text('');
				$('#usernameDetail').text('');
				$('#userRegistrationDateAndTimeDetail').text('');
				$('#userRoleDetail').text('');
				$('#currentPassword').text('');
				$('#usernameDetail').append('Username: ' + data.user.username);
				$('#userRegistrationDateAndTimeDetail').append('Registration time: ' + data.user.dateOutput);
				$('#userRoleDetail').append('Role: ' + data.user.role);
				$('#hiddenUserId').val(data.user.id);
				$('#currentPassword').val(data.user.password);
				
			})
		},
		
		populateUserProfileByUsername : function(ID) {
			params = {
					'user_name' : ID
			};
			$.get('UsernameServlet', params, function(data){
				var baseUrl = (window.location).href;
		    	var user_id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
				TicketManager.getAll(data.user.id);
				$('#hiddenUserId').text('');
				$('#usernameDetail').text('');
				$('#userRegistrationDateAndTimeDetail').text('');
				$('#userRoleDetail').text('');
				$('#currentPassword').text('');
				$('#usernameDetail').append('Username: ' + data.user.username);
				$('#userRegistrationDateAndTimeDetail').append('Registration time: ' + data.user.dateOutput);
				$('#userRoleDetail').append('Role: ' + data.user.role);
				$('#hiddenUserId').val(data.user.id);
				$('#currentPassword').val(data.user.password);
				
			})
		},
		
		populateCurrentUserProfile : function() {
			params = {
				'action': 'loggedInUserId'	
			};
			$.get('UseridServlet', params, function(data){
				window.location.href = "#";
		        window.location.href += '?id=' + data.loggedInUserId;
				UsersManager.populateUserProfile(data.loggedInUserId);
				
			})
		},
		
		updateUserPassword: function(ID, pass) {
			params = {
					'action': 'updateUserPassword',
					'user-id': ID,
					'password': pass
			};
			$.post('UserServlet', params, function(data){
				console.log(data);
			})
		},
		
		updateUserRole: function(ID, user_role) {
			params = {
					'action': 'updateUserRole',
					'user-id': ID,
					'role': user_role
			};
			$.post('UserServlet', params, function(data){
				console.log(data);
			})
		},
		
		deleteUser: function(ID) {
			params = {
					'action': 'deleteUser',
					'user-id': ID
			};
			$.post('UserServlet', params, function(data){
				console.log(data);
			})
		},
		
		deleteUserLogic: function(ID) {
			params = {
					'action': 'deleteUserLogic',
					'user-id': ID
			};
			$.post('UserServlet', params, function(data){
				console.log(data);
			})
		},
		
		
		
		registerUser : function() {
			var regUsername = $('#RegUsername').val();
			var regPassword = $('#RegPassword').val();
			params = {
					'action':'registerUser',
					'registerUsername': regUsername,
					'registerPassword': regPassword
					
			};
			$.post('UserServlet', params, function(data){
				if(data.message === "Username already taken!") {
					$('#RegisterForm').append('<p style="color:red">' + data.message + '</p>');
				}else {
					$('#RegisterForm').hide();
			    	StateManager.loggingState();
			    	alert('Successfully registered, please log in now!');
				}
			});
		},
		
		searchUsers : function() {
			var $searchUserRoleDropdown = $('#searchUserRole').find('option:selected');
			var usernameSearchInput = $('#usernameInputSearch').val();
			var userRole = $searchUserRoleDropdown.val();
			if (userRole === 'Select role') {
				userRole = '';
			}
			var params = {
					'usernameSearchInput': usernameSearchInput,
					'roleSearchInput': userRole
			};
			$('#usersTable1').dataTable().fnDestroy();
			$.get('SearchUsersServlet', params, function(data){
				$('#usersTable1').DataTable ({
					data: data.filteredUsers,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'username',
							render:function(data, type, row){
								return '<a id="userRedirectFromTable" data-id="' + row.id + '" href="#">' + data + '</a>';
								}
							},
							{data : 'dateOutput'},
							{data : 'role'}
						]
				})
			})
		}
}

var StateManager = {
		
		initState : function(){
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#ProfileBtn').show();
					$('#UsersBtn').show();
					$('#LoginBtn').hide();
					$('#RegisterBtn').hide();
					$('#LogoutBtn').show();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').hide();
					$('#movieDetail').hide();
					$('#projectionDetail').hide();
					$('#movieCommands').hide();
					$('#addProjectionForm').hide();
					$('#addNewProjection').show();
					$('#projectionsSection').show();
					$('#projectionsCommands').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#usersSection').hide();
					$('#ticketDetail').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				}
				else if(data.loggedInUserRole === "USER") {
					$('#ProfileBtn').show();
					$('#UsersBtn').hide();
					$('#LoginBtn').hide();
					$('#RegisterBtn').hide();
					$('#LogoutBtn').show();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').hide();
					$('#movieDetail').hide();
					$('#projectionDetail').hide();
					$('#movieCommands').hide();
					$('#addProjectionForm').hide();
					$('#addNewProjection').hide();
					$('#projectionsSection').show();
					$('#projectionsCommands').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#usersSection').hide();
					$('#ticketDetail').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				}else {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').hide();
					$('#movieDetail').hide();
					$('#projectionDetail').hide();
					$('#movieCommands').hide();
					$('#addProjectionForm').hide();
					$('#addNewProjection').hide();
					$('#projectionsCommands').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#usersSection').hide();
					$('#ticketDetail').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
			}
			})
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
			$('#projectionsSection').hide();
			$('#projectionsCommands').hide();
			$('#projectionDetail').hide();
			$('#buyTicketFromMovieSection').hide();
			$('#usersSection').hide();
			$('#projectionTicketsTable').hide();
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
			$('#projectionsSection').hide();
			$('#projectionsCommands').hide();
			$('#projectionDetail').hide();
			$('#buyTicketFromMovieSection').hide();
			$('#usersSection').hide();
			$('#projectionTicketsTable').hide();
		},
		
		usersState: function(){
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			$('#projectionsSection').hide();
			$('#projectionsCommands').hide();
			$('#projectionDetail').hide();
			$('#buyTicketFromMovieSection').hide();
			$('#usersSection').show();
			$('#singleTicket').hide();
			$('#projectionTicketsTable').hide();
		},
		
		moviesState: function() {
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').show();
					$('#addMovieForm').hide();
					$('#movieDetail').hide();
					$('#movieCommands').hide();
					$('#projectionsSection').hide();
					$('#projectionDetail').hide();
					$('#addNewMovie').show();
					$('#ProfileBtn').show();
					$('#UsersBtn').show();
					$('#LogoutBtn').show();
					$('#projectionsCommands').hide();
					$('#usersSection').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#moviesTable1').dataTable().fnSetColumnVis(5, true);
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
					
				}
				else if(data.loggedInUserRole === "USER") {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').show();
					$('#addMovieForm').hide();
					$('#movieCommands').hide();
					$('#projectionsSection').hide();
					$('#addNewMovie').hide();
					$('#ProfileBtn').show();
					$('#projectionsCommands').hide();
					$('#LogoutBtn').show();
					$('#usersSection').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				}else {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').show();
					$('#addMovieForm').hide();
					$('#movieDetail').hide();
					$('#projectionsSection').hide();
					$('#addNewMovie').hide();
					$('#projectionsCommands').hide();
					$('#projectionDetail').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#usersSection').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
			}
			})
		},
		
		detailedMovieState: function(){
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#movieDetail').show();
					$('#movieCommands').show();
					$('#updateMovieForm').hide();
					$('#projectionsCommands').hide();
					$('#deleteMovieFromDetail').show();
					$('#updateMovieFromDetail').show();
					$('#buyTicketFromMovie').hide();
					$('#moviesSection').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionDetail').hide();
					$('#usersSection').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				}
				else if(data.loggedInUserRole === "USER") {
					$('#movieDetail').show();
					$('#movieCommands').show();
					$('#deleteMovieFromDetail').hide();
					$('#updateMovieFromDetail').hide();
					$('#buyTicketFromMovie').show();
					$('#updateMovieForm').hide();
					$('#moviesSection').hide();
					$('#projectionsCommands').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionDetail').hide();
					$('#usersSection').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				} else {
					$('#movieDetail').show();
					$('#updateMovieForm').hide();
					$('#moviesSection').hide();
					$('#projectionsCommands').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionDetail').hide();
					$('#usersSection').hide();
					$('#userDetail').hide();
					$('#singleTicket').hide();
					$('#projectionTicketsTable').hide();
				}
			});
		},
		
		detailedProjectionState : function() {
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#projectionDetail').show();
					$('#projectionsSection').hide();
					$('#projectionsCommands').show();
					$('#buyTicket').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionTicketsTable').show();
					
						
				} else if(data.loggedInUserRole === "USER") {
					$('#projectionsSection').hide();
					$('#projectionsCommands').show();
					$('#deleteProjectionFromDetail').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionDetail').show();

				} else {
					$('#projectionsSection').hide();
					$('#buyTicketFromMovieSection').hide();
					$('#projectionDetail').show();
			
				}
			});
		},
		
		buyTicketFromMovie : function(id){
			params = {
					'action': 'getProjectionsByMovie',
					'id': id
			};
			$.post('ProjectionsServlet', params, function(data){
				$('#buyTicketForMovie').DataTable ({
					data: data.projections,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'movie',
							render:function(data, type, row){
								return '<a id="movieRedirectFromBuyTicket" data-name="' + data + '" href="#">' + data + '</a>';
								}
							},
							{
								"data" : 'dateOutput',
								render:function(data, type, row){
									return '<a id="projectionRedirectFromBuyTicket" data-id="' + row.id +'" href="#">' + data + '</a>';
								}
									
							},
							{data : 'projectionType'},
							{data : 'theater'},
							{data : 'ticketPrice'},
							{
								"data": null,
								render:function(data, type, row)
								{
									return '<button id="chooseProjection" data-id="' + data.id + '">Choose</button>'
								},
								"targets": -1
							}
						]
				})
			})
			
		},
		
		userProfileState : function () {
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#projectionsSection').hide();
					$('#userDetail').show();
					$('#ticketsTable1').dataTable().fnDestroy();
					$('#buyTicket').hide();
			    	$('#ticketDetail').hide();
			    	$('#usersSection').hide();
			    	$('#moviesSection').hide();
			    	$('#singleTicket').hide();
			    	$('#movieDetail').hide();
			    	$('#movieCommands').hide();
			     	$('#projectionDetail').hide();
			    	$('#projectionCommands').hide();
			    	$('#buyTicketFromMovieSection').hide();
			    	$('#projectionTicketsTable').hide();
			    	$('#updateUserPassword').hide();
			    	$('#updateUserRole').hide();
				} else if(data.loggedInUserRole === "USER") {
					$('#projectionsSection').hide();
					$('#ticketsTable1').dataTable().fnDestroy();
					$('#userDetail').show();
					$('#changeUserRoleAdmin').hide();
					$('#deleteUser').hide();
					$('#deleteTicket').hide();
					$('#buyTicket').hide();
			    	$('#ticketDetail').hide();
			    	$('#usersSection').hide();
			    	$('#moviesSection').hide();
			    	$('#singleTicket').hide();
			    	$('#movieDetail').hide();
			    	$('#movieCommands').hide();
			    	$('#projectionDetail').hide();
			    	$('#projectionCommands').hide();
			    	$('#buyTicketFromMovieSection').hide();
			    	$('#singleTicketBuyer').hide();
			    	$('#projectionTicketsTable').hide();
			    	$('#updateUserPassword').hide();
			    	$('#updateUserRole').hide();
				}
			});
		}
		
}




$(document).ready(function() {
	
	ProjectionsManager.getAll();
	ProjectionsManager.populateMovieDropdown();
	ProjectionsManager.populateProjectionTypeDrowpdown();
	ProjectionsManager.populateTheaterDropdown();
	
	StateManager.initState();
	
	MoviesManager.getAll();
	UsersManager.getAll();
	
	$('#searchMovieBtn').click(function(e) {
		e.preventDefault();
		var $searchOptionBox = $('#searchOptionsBox').find('option:selected'); 
		params = {
				'action':'loggedInUserRole'
		};
		$.get('UserServlet', params, function(data){
			if(data.loggedInUserRole === "ADMIN") {
				MoviesManager.searchMoviesAdmin();
			}
			if(data.loggedInUserRole === "USER"){
				MoviesManager.searchMovies();
			}
			if($searchOptionBox.val() === 'searchMoviesBy' && data.loggedInUserRole === "ADMIN"){
				MoviesManager.searchMoviesByDurationAdmin();
			}
			if($searchOptionBox.val() === 'searchMoviesBy' && data.loggedInUserRole === "USER"){
				MoviesManager.searchMoviesByDuration();
			}
			
		});
		MoviesManager.searchMovies();
		if($searchOptionBox.val() === 'searchMoviesBy'){
			MoviesManager.searchMoviesByDuration();
		}
		console.log($searchOptionBox.val());
	});
	
	
	$('#btnConfirmLogin').click(function(e) {
		e.preventDefault();
		UsersManager.logIn();
	});
	
	
	$('#changePassword').click(function(e) {
		e.preventDefault();
		$('#updateUserPassword').toggle();
	});
	
	$('#changeUserRoleAdmin').click(function(e) {
		e.preventDefault();
		$('#updateUserRole').toggle();
	});
	
	$('#confirmPassword').click(function(e){
		e.preventDefault();
		var user_id = $('#hiddenUserId').val();
		var password = $('#newPassword').val();
		UsersManager.updateUserPassword(user_id, password);
		$('#currentPassword').text('');
		$('#currentPassword').val(password);
		alert('Successfully changed password');
		
	});
	
	$('#confirmRole').click(function(e){
		e.preventDefault();
		var user_id = $('#hiddenUserId').val(); 
		var $role = $('#newUserRole').find('option:selected'); 
		UsersManager.updateUserRole(user_id, $role.val());
		$('#userRoleDetail').text('');
		$('#userRoleDetail').append('Role: ' + $role.val());
		alert('Successfully changed role');
	});
	
	$('#deleteUser').click(function(e){
		e.preventDefault();
		var user_id = $('#hiddenUserId').val();
		var table = $('#ticketsTable1').DataTable();
		if (! table.data().any()) {
			UsersManager.deleteUser(user_id);
			$('#userDetail').hide();
			$('#usersTable1').dataTable().fnDestroy();
			UsersManager.getAll();
			StateManager.usersState();
			alert('Successfully deleted user!');
		} else {
			UsersManager.deleteUserLogic(user_id);
			$('#userDetail').hide();
			$('#usersTable1').dataTable().fnDestroy();
			UsersManager.getAll();
			StateManager.usersState();
			alert('Successfully deleted user!');
		}
		
		
	});
	
	$('#LogoutBtn').click(function(e) {
		e.preventDefault();
		UsersManager.logOut();
		location.reload();
	});
	
	$('#UsersBtn').click(function(e){
		e.preventDefault();
		StateManager.usersState();
		$('#userDetail').hide();
	});
	
	$('#buyTicketFromMovie').click(function(e){
		var baseUrl = (window.location).href;
    	var id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
		$('#buyTicketForMovie').dataTable().fnDestroy();
    	StateManager.buyTicketFromMovie(id);
    	$('#buyTicketFromMovieSection').show();
    	$('#movieDetail').hide();
		$('#movieCommands').hide();
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
        StateManager.moviesState();
	});
	
	$('#submitNewProjection').click(function(e){
		ProjectionsManager.addProjection();
		$('#projectionsTable1').dataTable().fnDestroy();
		ProjectionsManager.getAll();
	})
	
	
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
	
	$('#HomeBtn').click(function(e){
		e.preventDefault();
		location.reload();
	});
	
	
    $('body').on('click', '#deleteMovie', function(e){
        var id= $(this).attr("data-id");
        MoviesManager.deleteMovie(id);
        $(this).parents("tr").remove();
        $('#moviesTable1').DataTable().fnDestroy();
        MoviesManager.getAll();
        $('#moviesTable1').dataTable().fnSetColumnVis(5, true);
        
    });
    
    $('body').on('click', '#movieRedirect', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        MoviesManager.getMovie(id);
        StateManager.detailedMovieState();

    });
    
    
    
    $('body').on('click', '#movieRedirectFromProjection', function(e){
        var name= $(this).attr("data-name");
        
		MoviesManager.getMovieByTitle(name);
		StateManager.detailedMovieState();
		$('#projectionsSection').hide();
      
    });
    
    
    
    $('body').on('click', '#movieRedirectFromProjectionDetail', function(e){
        var name= $(this).attr("data-name");
		MoviesManager.getMovieByTitle(name);
		StateManager.detailedMovieState();
		$('#ticketDetail').hide();
		$('#buyTicketFromMovieSection').hide();
      
    });
    
    $('body').on('click', '#movieRedirectFromSingleTicket', function(e){
        var name= $(this).attr("data-name");
		MoviesManager.getMovieByTitle(name);
		StateManager.detailedMovieState();
		$('#singleTicket').hide();
      
    });
    
    $('body').on('click', '#movieRedirectFromTicketDetail', function(e){
        var name= $(this).attr("data-name");
		MoviesManager.getMovieByTitle(name);
		StateManager.detailedMovieState();
		$('#projectionsSection').hide();
		$('#projectionDetail').hide();
		$('#ticketDetail').hide();
      
    });
    
    
    
    
    $('body').on('click', '#movieRedirectFromBuyTicket', function(e){
        var name= $(this).attr("data-name");
		MoviesManager.getMovieByTitle(name);
		StateManager.detailedMovieState();
		$('#projectionsSection').hide();
		$('#projectionDetail').hide();
		$('#movieDetail').hide();
      
    });
    
    
    $('body').on('click', '#projectionRedirect', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        ProjectionsManager.getProjection(id);
        StateManager.detailedProjectionState();
              
    });
    
    $('body').on('click', '#chooseProjection', function(e){
        var id= $(this).attr("data-id");
        TicketManager.populateTicketDetails(id);
        $('#ticketDetail').show();
        e.target.href = "#";
        e.target.href += '?id=' + id;
              
    });
    
    $('body').on('click', '#projectionRedirectFromSingleTicket', function(e){
        var id = $('#hiddenProjectionId').val();
        ProjectionsManager.getProjection(id);
        StateManager.detailedProjectionState();
        $('#userDetail').hide();
        $('#singleTicket').hide();
              
    });
    
    
    
    $('body').on('click', '#projectionRedirectFromBuyTicket', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        ProjectionsManager.getProjection(id);
        StateManager.detailedProjectionState();
        $('#movieDetail').hide();
		$('#movieCommands').hide();
        
              
    });
    
    
    
    $('body').on('click', '#projectionRedirectFromTicketDetail', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        ProjectionsManager.getProjection(id);
        StateManager.detailedProjectionState();
        $('#ticketDetail').hide();
        $('#buyTicket').show();
                      
    });
    
    $('body').on('click', '#userRedirectFromTable', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        UsersManager.populateUserProfile(id);
        StateManager.userProfileState();
        $('#usersSection').hide();
                      
    });
    
    
    $('body').on('click', '#userRedirectProjectionTicketsTable', function(e){
        var id= $(this).attr("data-name");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        UsersManager.populateUserProfileByUsername(id);
        StateManager.userProfileState();
        $('#projectionsSection').hide();
        $('#projectionsCommands').hide();
        $('#projectionTicketsTable').hide();
                      
    });
    
    $('body').on('click', '#userRedirectFromSingleTicket', function(e){
        var id= $(this).attr("data-name");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        UsersManager.populateUserProfileByUsername(id);
        StateManager.userProfileState();                      
    });
    
    
    
    $('body').on('click', '#ticketRedirectionFromTable', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        TicketManager.getSingleTicket(id);
        $('#singleTicket').show();
                      
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
		$('#addMovieForm').hide();
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
    
    $('#addNewProjection').click(function(e) {
    	$('#addProjectionForm').toggle();
    });
	
    $('#confirmPurchase').click(function(e){
    	TicketManager.purchaseTicket();
    })
    
    $('#buyTicket').click(function(e){
    	var baseUrl = (window.location).href;
    	var projection_id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
    	TicketManager.populateTicketDetails(projection_id);
    	$('#projectionDetail').hide();
    	$('#buyTicket').hide();
        $('#ticketDetail').show();
    });
    
    $('#ProfileBtn').click(function(e){
    	e.preventDefault();
    	UsersManager.populateCurrentUserProfile();
    	StateManager.userProfileState();
    	$('#projectionsCommands').hide();
    });
    
    $('#deleteTicket').click(function(e){
    	var baseUrl = (window.location).href;
    	var ticket_id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
    	var user_id = $('#hiddenUserId').val();
    	var projection_id = $('#hiddenProjectionDetailId').val();
    	TicketManager.deleteTicket(ticket_id);
    	$('#singleTicket').hide();
    	alert('Successfully deleted ticket!');
    	$('#ticketsTable1').dataTable().fnDestroy();
    	$('#projectionTicketsTable1').dataTable().fnDestroy();
		TicketManager.getAll(user_id);
		TicketManager.getAllByProjection(projection_id);
    	
    });
	
    $('#submitProjectionFilter').click(function(e){
    	e.preventDefault();
    	ProjectionsManager.searchProjections();
    });
    
    $('#btnConfirmRegister').click(function(e){
    	e.preventDefault();
    	UsersManager.registerUser();
    	
    })
    
    $('#submitUserFilter').click(function(e){
    	e.preventDefault();
    	UsersManager.searchUsers();
    });
    
    
    $('#deleteProjectionFromDetail').click(function(e){
    	e.preventDefault();
    	var id = $('#hiddenProjectionDetailId').val();
    	ProjectionsManager.deleteProjectionLogic(id);
    
    	
    	
    })
    
	
});