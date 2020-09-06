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
        print("waiting for data")
        new_data = conn.recv(9)[:-1]
        if new_data != b'':
            new_number = float(new_data)
            if new_number < 0:
                incoming_data = False
            else:
                print('recieved ' + str(new_number))
                data.append(new_number)
            ln.set_xdata(range(len(data)))
            ln.set_ydata(data)
            plt.plot(data)
            plt.draw()
    plt.show(data)
    s.close()
