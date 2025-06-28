
namespace InteligentServer.Data
{
    public static class DbInitializer
    {
        public static void Initializer(InteligentAppDbContext context)
        {
            context.Database.EnsureCreated();
        }
    }
}
