using Ighan.Analytics.StorageModels;

using Microsoft.EntityFrameworkCore;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ighan.Analytics.DataAccessLayer
{
    public class AnalyticsDbContext : DbContext
    {
        public AnalyticsDbContext(DbContextOptions<AnalyticsDbContext> options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }

        public DbSet<Project> Projects { get; set; }

        public DbSet<AnalyticKey> Keys { get; set; }

        public DbSet<AnalyticValue> Values { get; set; }
    }
}
