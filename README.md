###This Java API provides both server and client side components:
<ul>
    <li>Server: everything in the javax.websocket.server package.</li>
    <li>Client: the content of javax.websocket package, which consists of client side APIs and also common libraries to both server and client.</li>
</ul>

###WebSocket endpoint lifecycle events are handled by the following annotations:
<ul>
    <li>@ServerEndpoint: If decorated with @ServerEndpoint, the container ensures availability of the class as a WebSocket server listening to a specific URI space</li>
    <li>@ClientEndpoint: A class decorated with this annotation is treated as a WebSocket client</li>
    <li>@OnOpen: A Java method with @OnOpen is invoked by the container when a new WebSocket connection is initiated</li>
    <li>@OnMessage: A Java method, annotated with @OnMessage, receives the information from the WebSocket container when a message is sent to the endpoint</li>
    <li>@OnError: A method with @OnError is invoked when there is a problem with the communication</li>
    <li>@OnClose: Used to decorate a Java method that is called by the container when the WebSocket connection closes</li>
</ul>

###The WebSocket specification supports two on-wire data formats – text and binary
<ul>
<li>Text: Any textual data (java.lang.String, primitives or their equivalent wrapper classes)</li>
<li>Binary: Binary data (e.g. audio, image etc.) represented by a java.nio.ByteBuffer or a byte[] (byte array)</li>
<li>Java objects: The API makes it possible to work with native (Java object) representations in your code and use custom transformers (encoders/decoders) to convert them into compatible on-wire formats (text, binary) allowed by the WebSocket protocol</li>
<li>Ping-Pong: A javax.websocket.PongMessage is an acknowledgment sent by a WebSocket peer in response to a health check (ping) request</li>
</ul>