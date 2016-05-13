<%-- 
    Document   : manageEmployee
    Created on : May 13, 2016, 11:31:42 AM
    Author     : vietlv2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="page-content">
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">Modal title</h4>
                </div>
                <div class="modal-body">
                     Widget settings form goes here
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn blue">Save changes</button>
                    <button type="button" class="btn default" data-dismiss="modal">Close</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <!-- BEGIN PAGE HEADER-->
    <h3 class="page-title">
    Managed employee <small>managed employee samples</small>
    </h3>
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="index.html">Home</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">Data Tables</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">Managed employee</a>
            </li>
        </ul>
        <div class="page-toolbar">
            <div class="btn-group pull-right">
                <button type="button" class="btn btn-fit-height grey-salt dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
                Actions <i class="fa fa-angle-down"></i>
                </button>
                <ul class="dropdown-menu pull-right" role="menu">
                    <li>
                        <a href="#">Action</a>
                    </li>
                    <li>
                        <a href="#">Another action</a>
                    </li>
                    <li>
                        <a href="#">Something else here</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#">Separated link</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- END PAGE HEADER-->
    <!-- BEGIN PAGE CONTENT-->
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN EXAMPLE TABLE PORTLET-->
            <div class="portlet box grey-cascade">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-globe"></i>Managed employee
                    </div>
                    <div class="tools">
                        <a href="javascript:;" class="collapse">
                        </a>
                        <a href="#portlet-config" data-toggle="modal" class="config">
                        </a>
                        <a href="javascript:;" class="reload">
                        </a>
                        <a href="javascript:;" class="remove">
                        </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="btn-group">
                                    <button id="sample_editable_1_new" onclick="showModalAdd();" class="btn green">
                                    Add New <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover" id="sample_1">
                    <thead>
                    <tr>
                        <th class="table-checkbox">
                            <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/>
                        </th>
                        <th>
                             Username
                        </th>
                        <th>
                             Email
                        </th>
                        <th>
                             Name
                        </th>
                        <th>
                             Address
                        </th>
                        <th>
                             Tel
                        </th>
                        <th>
                             Action
                        </th>
                    </tr>
                    </thead>
                    <tbody class="employee-tbody">
                    
                    </tbody>
                    </table>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->
        </div>
    </div>
    <!-- END PAGE CONTENT-->
</div>
<!-- /.modal -->
<div id="add-responsive" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Add employee</h4>
            </div>
            <div class="modal-body">
                <div class="portlet-body form">
                    <form class="form-horizontal" role="form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">User ID</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="userId" placeholder="Enter text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control input-inline input-medium" name="name" placeholder="Enter text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Address</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control input-inline input-medium" name="address" placeholder="Enter text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Tel</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control input-inline input-medium" name="tel" placeholder="Enter text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Email Address</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                        <i class="fa fa-envelope"></i>
                                        </span>
                                        <input type="email" class="form-control" name="email" placeholder="Email Address">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Password</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <input type="password" class="form-control" name="password" placeholder="Password">
                                        <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                        </span>
                                    </div>
                                </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn default">Close</button>
                <button type="button" onclick="saveUser();" class="btn green">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function showModalAdd(){
        $("#add-responsive").modal("show");
    }

    function saveUser(){
        var userId = $("input[name=userId]").val();
        var name = $("input[name=name]").val();
        var address = $("input[name=address]").val();
        var tel = $("input[name=tel]").val();
        var email = $("input[name=email]").val();
        var password = $("input[name=password]").val();
        $.ajax({
            method: "POST",
            url: "LoginServlet?action=addEmployee",
            data: { userId: userId, name: name, address: address, tel: tel, email: email, password:password}
        }).done(function(data) {
            $("#add-responsive").modal("hide");
            window.location.href = "index.jsp";
        });
    }

    function deleteUser(userId){
        $.ajax({
            method: "POST",
            url: "LoginServlet?action=removeEmployee",
            data: { userId: userId}
        }).done(function(data) {
            window.location.href = "index.jsp";
        });
    }
</script>