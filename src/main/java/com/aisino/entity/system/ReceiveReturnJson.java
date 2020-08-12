package com.aisino.entity.system;

public class ReceiveReturnJson {
        /**
         *
         */
        private static final long serialVersionUID = 1L;


        // 0表示成功，1表示失败
        private int status = 0;

        private String msg = "";


        private Object obj = null;


        public ReceiveReturnJson() {
            super();
        }



        public ReceiveReturnJson(int status, String msg, Object obj) {
            this.status = status;
            this.msg = msg;
            this.obj = obj;
        }


        public ReceiveReturnJson(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        @Override
        public String toString() {
            return "Json{" +
                    "status=" + status +
                    ", msg='" + msg + '\'' +
                    ", obj=" + obj +
                    '}';
        }

}
