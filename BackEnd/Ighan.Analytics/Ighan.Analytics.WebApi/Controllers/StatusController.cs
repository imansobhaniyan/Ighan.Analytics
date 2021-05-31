using Ighan.Analytics.DataAccessLayer;

using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ighan.Analytics.WebApi.Controllers
{
    [Route("api/[controller]")]
    public class StatusController : ControllerBase
    {
        private readonly AnalyticsDbContext dbContext;

        public StatusController(AnalyticsDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        [HttpGet]
        public async Task<string> Get()
        {
            await dbContext.Users.FirstOrDefaultAsync();

            return "I`m up and running";
        }
    }
}
