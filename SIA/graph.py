import matplotlib.pyplot as plt
import time
import socket
import struct

host = "localhost"
port = 4444
data = []

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((host, port))
s.listen(2)
conn, addr = s.accept()

incoming_data = True

if __name__ == '__main__':
    # initialize figure
    plt.figure()
    ln, = plt.plot([])
    plt.ion()
    plt.draw()
    plt.xlabel("generacion")
    plt.ylabel("performance")
    while incoming_data:
        plt.pause(0.0001)
        #print("waiting for data")
        new_data = [float(i) for i in conn.recv(9 * 5).split(b'\n') if i != b'']
        end_signal = [i for i in new_data if i < 0]
        if end_signal:
            incoming_data = False
            new_data = new_data[:-1]
        if new_data:
            data.extend(new_data)
            ln.set_xdata(range(len(data)))
            ln.set_ydata(data)
            plt.draw()
        plt.plot(data, color='blue')
    plt.show(data)
    s.close()
