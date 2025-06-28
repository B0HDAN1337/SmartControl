using InteligentServer.Models;
using Microsoft.EntityFrameworkCore;

namespace InteligentServer.Data
{
    public class InteligentAppDbContext : DbContext
    {
        public InteligentAppDbContext(DbContextOptions<InteligentAppDbContext> options)
            : base(options)
        {
        }
    
    public DbSet<SensorData> SensorDatas { get; set; }
    }
}
