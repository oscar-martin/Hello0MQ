import org.zeromq.ZMQ

object Main{
    def main(args : Array[String]) {
        //  Prepare our context and socket
        val context = ZMQ.context(1)
        val socket = context.socket(ZMQ.REQ)

        socket.connect("tcp://localhost:5555")
        println("Connected to Server on localhost:5555")

        for (request_nbr <- 1 to 5)  {
            val request = "Hello ZQM"
            println("Sending request " + request_nbr + ": [" + request + "]")
            socket.send(request.getBytes, 0)

            //  Get the reply.
            val reply = socket.recv(0)
            println("Received reply " + request_nbr + ": [" + new String(reply) + "]")
        }
        val disconnectMsg = "disconnect".getBytes
        socket.send(disconnectMsg, 0)

        socket.close()
        context.term()
    }
}