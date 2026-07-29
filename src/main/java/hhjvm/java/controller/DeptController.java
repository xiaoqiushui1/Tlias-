package hhjvm.java.controller;
import hhjvm.java.anno.Log;
import hhjvm.java.pojo.Result;
import hhjvm.java.pojo.Dept;
import hhjvm.java.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class DeptController {
    @Autowired//依赖注入
    private DeptService deptService;

    //@RequestMapping("/depts")
    @GetMapping("/depts")//http://localhost:8080;反向代理的过程,为了负载均衡和隐私安全问题
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();//用Dept的list集合封装所有数据。
        return Result.success(deptList);//将数据以Result格式进行封装展示给前端,data转为Json格式。
    }
    //删除部门:利用httpservletrequst来获取
//    @DeleteMapping("/depts")
//    public Result delect(HttpServletRequest request){
//        String idstr=request.getParameter("id");
//        int id=Integer.parseInt(idstr);
//        System.out.println("根据部门id删除"+id);
//      return Result.success();
//  }`
//    @DeleteMapping("/depts")
//    public Result delete (@RequestParam ("id")Integer deptId){
//        System.out.println("根据部门id删除部门："+deptId);
//        return Result.success();
//     }
    //前端请求的参数名与服务器名一致才可以接收。
    @Log
    @DeleteMapping("/depts")
    public Result delete (Integer id){
        System.out.println("根据部门id删除部门："+id);
        deptService.deleteById(id);
        return Result.success();
    }
//添加部门
    @Log
    @PostMapping("/depts")
    public Result add( @RequestBody Dept dept){//将接受的json格式的对象封装成Dept对象
        System.out.println("新增部门:"+ dept);
        deptService.add(dept);
        return Result.success();//统一返回为result的格式；
    }
    //根据id查询部门,利用path variable来获取id
    @GetMapping("/depts/{id}")
    public Result abject(@PathVariable("id") Integer Id){
        System.out.println("根据部门id修改数据:"+Id);
     Dept dept=deptService.getById(Id);//用dept对象封装数据
        return Result.success(dept);//根据id查询D修改的数据
    }
    //根据接受的json格式的数据修改数据
    @Log
    @PutMapping("/depts")
    public Result undapt(@RequestBody Dept dept){
        System.out.println("修改部门"+dept);
        deptService.update(dept);
        return Result.success();
    }


}


