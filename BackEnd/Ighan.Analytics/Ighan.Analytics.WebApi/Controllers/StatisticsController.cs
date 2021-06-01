using Ighan.Analytics.DataAccessLayer;
using Ighan.Analytics.WebApi.Models.Statistics;
using Ighan.Analytics.WebApi.ViewModels.Common;

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
    public class StatisticsController : ControllerBase
    {
        private readonly AnalyticsDbContext dbContext;

        public StatisticsController(AnalyticsDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        [HttpPost]
        public async Task<ApiResult> Post(StatisticsModel model)
        {
            try
            {
                var project = await dbContext.Projects
                    .Include(f => f.AnalyticKeys)
                    .FirstOrDefaultAsync(f => f.Token == model.Token);

                if (project == null)
                    throw new Exception("Invalid token");

                var key = project.AnalyticKeys.FirstOrDefault(f => f.Key == model.Key);

                if(key == null)
                {
                    key = new StorageModels.AnalyticKey
                    {
                        Key = model.Key
                    };

                    project.AnalyticKeys.Add(key);
                }

                key.Values.Add(new StorageModels.AnalyticValue
                {
                    Brand = model.Brand,
                    CreateDate = DateTime.Now,
                    Manufacturer = model.Manufacturer,
                    Model = model.Model,
                    Release = model.Release,
                    SDK = model.SDKInt,
                    Value = model.Value
                });

                await dbContext.SaveChangesAsync();

                return new ApiResult
                {
                    Success = true
                };
            }
            catch (Exception exception)
            {
                return new ApiResult
                {
                    Message = exception.Message
                };
            }
        }
    }
}
