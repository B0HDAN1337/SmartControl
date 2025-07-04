using Microsoft.EntityFrameworkCore;
using InteligentServer.Data;
using InteligentServer.Repository;
using InteligentServer.Service;

namespace InteligentServer
{
    public class Program
    {
        public static void Main(string[] args)
        {

            var builder = WebApplication.CreateBuilder(args);

            builder.Services.AddAuthorization();
            builder.Services.AddControllers().AddJsonOptions(options =>
            {
                options.JsonSerializerOptions.PropertyNameCaseInsensitive = true;
            });

            builder.Services.AddControllers();
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            // Add DbContext
            builder.Services.AddDbContext<InteligentAppDbContext>(options =>
            options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

            // Repository
            builder.Services.AddScoped<ISensorDataRepository, SensorDataRepository>();

            // Service
            builder.Services.AddScoped<ISensorDataService, SensorDataService>();

            // Cors
            builder.WebHost.UseUrls("http://0.0.0.0:2000");

            builder.Services.AddCors(options =>
            {
                options.AddPolicy("AllowAll",
                    builder => builder.AllowAnyOrigin().AllowAnyHeader().AllowAnyMethod());
            });

            var app = builder.Build();

            using (var scope = app.Services.CreateScope())
            {
                var context = scope.ServiceProvider.GetRequiredService<InteligentAppDbContext>();
                DbInitializer.Initializer(context);
            }


            // Configure the HTTP request pipeline.
            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            app.MapControllers();

            app.UseCors("AllowAll");

            app.Run();
        }
    }
}
